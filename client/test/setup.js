import { JSDOM } from 'jsdom';

const dom = new JSDOM('<!DOCTYPE html><html><head></head><body></body></html>');

global.window = dom.window;
global.document = dom.window.document;
/*
 Object.keys(win).forEach(property => {
 if (typeof global[property] === 'undefined') {
 global[property] = win[property];
 }
 });
 */

global.navigator = {
    userAgent: 'node.js'
};

import chai from 'chai'
import chaiEnzyme from 'chai-enzyme'
chai.use(chaiEnzyme());