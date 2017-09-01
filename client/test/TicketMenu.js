import React from 'react';
import TicketMenu from '../src/js/components/ticket/TicketMenu';
import { Row, Glyphicon, Button } from 'react-bootstrap';
import {mount, render, shallow} from 'enzyme';
import { expect } from 'chai';

describe('Ticket menu', () => {
    const department = {
        id: 1,
        name: 'DEPARTMENT'
    };
    const user = {
        name: "John",
        surname: "Black",
        type: {
            id: 3,
            name: "Administrator"
        },
        position: 'POSITION',
        id: 4,
        department: department
    };
    const props = {
        user: user,
        token: "TOKEN",
        closed: false,
        ticket: {
            title: 'TITLE',
            author: user,
            rate: 4,
            created: 1500156000000,
            lastModified: 1500157000000,
            department: department
        }
    };
    //opened, without recipient, user from target department, user is admin
    const wrapper = mount(<TicketMenu {...props} />);

    const user2 = {
        ...user,
        id: 5,
        type: {
            ...user.type,
            id: 1
        }
    };
    const props2 = {
        ...props,
        user: user2,
        ticket: {
            ...props.ticket,
            author: user2,
            recipient: user
        }
    };
    //opened, with logged author, recipient is chosen, author is not admin
    const wrapper2 = mount(<TicketMenu {...props2} />);

    const props3 = {
        ...props2,
        ticket: {
            ...props2.ticket,
            author: user,
            recipient: null
        }
    };
    //opened, recipient is not chosen, user can take it
    const wrapper3 = mount(<TicketMenu {...props3} />);

    const props4 = {
        ...props,
        user: user,
        closed: true,
        ticket: {
            ...props.ticket,
            author: user,
            recipient: user2,
            rate: null,
            closed: 1500158000000
        }
    };
    //closed, recipient is chosen, author is logged
    const wrapper4 = mount(<TicketMenu {...props4} />);

    it('has correct close button', () => {
        expect(wrapper.find(Row)).to.have.descendants('.glyphicon-ok');
        expect(wrapper2.find(Row)).to.have.descendants('.glyphicon-ok');
        expect(wrapper3.find(Row)).to.not.have.descendants('.glyphicon-ok');
        expect(wrapper4.find(Row)).to.not.have.descendants('.glyphicon-ok');
    });

    it('has correct take button', () => {
        expect(wrapper.find(Row)).to.not.have.descendants('.glyphicon-check');
        expect(wrapper2.find(Row)).to.not.have.descendants('.glyphicon-check');
        expect(wrapper3.find(Row)).to.have.descendants('.glyphicon-check');
        expect(wrapper4.find(Row)).to.not.have.descendants('.glyphicon-check');
    });

    it('has correct rate button', () => {
        expect(wrapper.find(Row)).to.not.have.descendants('.glyphicon-thumbs-up');
        expect(wrapper2.find(Row)).to.not.have.descendants('.glyphicon-thumbs-up');
        expect(wrapper3.find(Row)).to.not.have.descendants('.glyphicon-thumbs-up');
        expect(wrapper4.find(Row)).to.have.descendants('.glyphicon-thumbs-up');
    });

    it('has correct remove button', () => {
        expect(wrapper.find(Row)).to.have.descendants('.glyphicon-erase');
        expect(wrapper2.find(Row)).to.not.have.descendants('.glyphicon-erase');
        expect(wrapper3.find(Row)).to.not.have.descendants('.glyphicon-erase');
        expect(wrapper4.find(Row)).to.have.descendants('.glyphicon-erase');
    });

    /*it('has correct avatar logo', () => {
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
    });*/
});