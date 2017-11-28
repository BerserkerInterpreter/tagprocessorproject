import React from "react";

import { applyMiddleware, createStore } from "redux";
import logger from "redux-logger";
import thunk from "redux-thunk";
import axios from "axios";

const estadoInicial = {
  trayendo: false,
  traido: false,
  usuarios: [],
  error: null
};

const tagNumeral = /#/g;
const saltoLinea = /\n/g;
const arroba = /@/g;

const entidadUrlNumeral = "%23";
const entidadUrlSaltoLinea = "%5Cn";
const entidadUrlArroba = "%40";

export default class Form extends React.Component {
  state = {
    codigoTags: "",
    codigoHtmlProcesado: ""
  };

  cambiarEstado = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  procesarUrl = e => {
    var url = "http://localhost:8080/api/procesartag?datosTag=";
    var arrayCodigoTags = this.state.codigoTags.split(" ");
    for(var indice = 0; indice < arrayCodigoTags.length; indice++) {
      url += arrayCodigoTags[indice] + "%20";
    }
    url = url.replace(tagNumeral, entidadUrlNumeral);
    url = url.replace(saltoLinea, entidadUrlSaltoLinea);
    url = url.replace(arroba, entidadUrlArroba);
    return url;
  };

  enviarDatos = url => {
    axios.get(url)
      .then((respuesta) => {
        //dispatch({type: "RECEIVE_USERS", payload: response.data })
        console.log("Al recibir los datos.");
        console.log(respuesta.data);
        this.state.codigoHtmlProcesado = respuesta.data;
        this.setState({
          ["codigoHtmlProcesado"]: respuesta.data
        });
      })
      .catch((err) => {
        // dispatch({type: "FETCH_USERS_ERROR", payload: err })
        console.log('An error has ocurred.');
      })
  };

  enviar = e => {
    e.preventDefault();
    console.log(this.state);
    console.log(this.state.tagsCode);
    var url = this.procesarUrl();
    console.log(url);
    url = url.replace(tagNumeral, entidadUrlNumeral);
    url = url.replace(saltoLinea, entidadUrlSaltoLinea);
    console.log(url);
    this.enviarDatos(url);
  };

  render() {
    return (
      <div>
        <h2>Tag Processor App</h2>
        <br/>
        <div>
          <h3>Introduce el codigo de tags deseado</h3>
          <form>
            <textarea
              name="codigoTags" rows="4" cols="50"
              value={this.state.codigoTags}
              onChange={e => this.cambiarEstado(e)}>
              Hola!!!!
            </textarea>
            <br/>
            <br/>
            <button onClick={e => this.enviar(e)}>Submit</button>
          </form>
        </div>
        <br/>
        <div>
          <h3>Resultado del codigo ejecutado en HTML</h3>
          <form>
            <textarea
              name="codigoHtmlProcesado" rows="4" cols="50"
              value={this.state.codigoHtmlProcesado}
              onChange={e => this.cambiarEstado(e)}>
              Html code
            </textarea>
          </form>
        </div>
      </div>
    );
  }

}
