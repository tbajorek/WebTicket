import React from 'react';
import Reflux from 'reflux';
import axios from 'axios';
import MessageActions from '../actions/MessageActions';

/**
 * Abstract store which provides AJAX functionality
 */
class AbstractAjaxStore extends Reflux.Store {
    constructor() {
        super();
        this.state = {};
    }

    fetchData(method, endpoint, token = undefined, inputData = undefined, onSuccess = (response) => {}, onError = (error) => {}) {
        var context = this;
        var apiMainUrl = location.href.substr(0, location.href.indexOf('#'));
        axios({
            baseURL: apiMainUrl+'api/',
            method: method,
            url: endpoint,
            data: inputData,
            timeout: 6000,
            withCredentials: true,
            headers: {
                'X-Token': token
            }
        }).then(function (response) {
            MessageActions.clearMessages();
            var response = response.data;
            for(var i in response.messages.success) {
                MessageActions.addSuccess(response.messages.success[i]);
            }
            for(var i in response.messages.error) {
                MessageActions.addError(response.messages.error[i]);
            }
            for(var i in response.messages.warning) {
                MessageActions.addWarning(response.messages.warning[i]);
            }
            for(var i in response.messages.info) {
                MessageActions.addInfo(response.messages.info[i]);
            }
            if(typeof response.data !== 'undefined') {
                context.setState(response.data);
            }
            onSuccess(response);
        })
        .catch(function (error) {
            onError(error);
            MessageActions.clearMessages();
            MessageActions.addError('Błąd aplikacji: '+error.message);
        });
    }

    getData(endpoint, token = undefined, onSuccess = (response) => {}, onError = (error) => {}) {
        this.fetchData('get', endpoint, token, undefined, onSuccess, onError);
    }

    postData(endpoint, token = undefined, inputData = undefined, onSuccess = (response) => {}, onError = (error) => {}) {
        this.fetchData('post', endpoint, token, inputData, onSuccess, onError);
    }

    putData(endpoint, token = undefined, inputData = undefined, onSuccess = (response) => {}, onError = (error) => {}) {
        this.fetchData('put', endpoint, token, inputData, onSuccess, onError);
    }

    deleteData(endpoint, token = undefined, onSuccess = (response) => {}, onError = (error) => {}) {
        this.fetchData('delete', endpoint, token, undefined, onSuccess, onError);
    }
}

export default AbstractAjaxStore;