import React from 'react';
import Error from '../src/js/components/message/Error';
import Warning from '../src/js/components/message/Warning';
import Info from '../src/js/components/message/Info';
import Success from '../src/js/components/message/Success';
import {mount, render, shallow} from 'enzyme';
import { expect } from 'chai';

describe('Error', () => {
    it('has correct content', () => {
        const content = "Message";
        const wrapper = mount(<Error message={content} />);
        expect(wrapper.find('div').text()).to.have.string(content);
    });
});

describe('Warning', () => {
    it('has correct content', () => {
        const content = "Message";
        const wrapper = mount(<Warning message={content} />);
        expect(wrapper.find('div').text()).to.have.string(content);
    });
});

describe('Info', () => {
    it('has correct content', () => {
        const content = "Message";
        const wrapper = mount(<Info message={content} />);
        expect(wrapper.find('div').text()).to.have.string(content);
    });
});

describe('Success', () => {
    it('has correct content', () => {
        const content = "Message";
        const wrapper = mount(<Success message={content} />);
        expect(wrapper.find('div').text()).to.have.string(content);
    });
});