import React from 'react';
import MessageView from '../src/js/components/ticket/message/MessageView';
import Rate from 'rc-rate';
import {mount, render, shallow} from 'enzyme';
import { expect } from 'chai';

import { Glyphicon, Button } from 'react-bootstrap';

describe('Message view', () => {
    const user = {
        name: "John",
        surname: "Black",
        type: {
            id: 3,
            name: "Administrator"
        },
        id: 4
    };
    const props = {
        user: user,
        token: "TOKEN",
        closed: false,
        message: {
            content: "CONTENT",
            created: 1500156000000,
            author: user
        }
    };
    const wrapper = mount(<MessageView {...props} />);

    const props2 = {
        ...props,
        user: {
            ...props.user,
            id: 15
        },
        closed: true
    };
    const wrapper2 = mount(<MessageView {...props2} />);

    it('has correct position of container', () => {
        expect(wrapper.find('li')).to.have.className('left');
        expect(wrapper2.find('li')).to.have.className('right');
    });

    it('has correct avatar logo', () => {
        expect(wrapper.find('img')).to.have.attr('src', 'http://placehold.it/50/FA6F57/fff&text=JB');
        expect(wrapper2.find('img')).to.have.attr('src', 'http://placehold.it/50/55C1E7/fff&text=JB');
    });

    it('has correct position of author block', () => {
        expect(wrapper.find('strong')).to.not.have.className('pull-right');
        expect(wrapper2.find('strong')).to.have.className('pull-right');
    });

    it('has correct position of date block', () => {
        expect(wrapper.find('small')).to.have.className('pull-right');
        expect(wrapper2.find('small')).to.not.have.className('pull-right');
    });

    it('has correct content', () => {
        expect(wrapper.find('p')).to.have.text(props.message.content);
    });

    it('contains correct created date', () => {
        expect(wrapper.find('small')).to.contain.text((new Date(props.message.created)).toDateString());
    });

    it('has no menu button when ticket is not closed', () => {
        expect(wrapper.find('.message-menu')).to.have.exactly(2).descendants(Button);
        expect(wrapper2.find('.message-menu')).to.have.exactly(0).descendants(Button);
    });
});