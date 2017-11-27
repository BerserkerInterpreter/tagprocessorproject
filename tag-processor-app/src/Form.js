import React from 'react';

import { applyMiddleware, createStore } from "redux";
import logger from "redux-logger";
import thunk from "redux-thunk";
import axios from "axios";

const initialState = {
  fetching: false,
  fetched: false,
  users: [],
  error: null
};

export default class Form extends React.Component {
  state = {
    tagsCode: "tdfd",
    processedHtmlCode: "fff"
  };

  change = e => {
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  onSubmit = e => {
    e.preventDefault();
    console.log(this.state);
    console.log(this.state.tagsCode);
    var url = "http://localhost:8080/api/procesartag?tagData=";
    var arrayTagsCode = this.state.tagsCode.split(" ");
    for(var i = 0; i < arrayTagsCode.length; i++) {
      url += arrayTagsCode[i] + "%20";
    }
    console.log(url);
    url = url.replace(/#/g,"%23");
    url = url.replace(/\n/g,"%5Cn");
    console.log(url);
    axios.get(url)
      .then((response) => {
        //dispatch({type: "RECEIVE_USERS", payload: response.data })
        console.log("Al recibir los datos.");
        console.log(response.data);
        this.state.processedHtmlCode = response.data;
        this.setState({
          ["processedHtmlCode"]: response.data
        });
      })
      .catch((err) => {
        // dispatch({type: "FETCH_USERS_ERROR", payload: err })
        console.log('An error has ocurred.');
      })
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
              name="tagsCode" rows="4" cols="50"
              value={this.state.tagsCode}
              onChange={e => this.change(e)}>
              Hola!!!!
            </textarea>
            <br/>
            <br/>
            <button onClick={e => this.onSubmit(e)}>Submit</button>
          </form>
        </div>
        <br/>
        <div>
          <h3>Resultado del codigo ejecutado en HTML</h3>
          <form>
            <textarea
              name="processedHtmlCode" rows="4" cols="50"
              value={this.state.processedHtmlCode}
              onChange={e => this.change(e)}>
              Html code
            </textarea>
          </form>
        </div>
      </div>
    );
  }

}
