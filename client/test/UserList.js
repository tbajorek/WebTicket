import React from 'react';
import UserList from '../src/js/components/department/UserList';
import { Table, DropdownButton, Label } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import {mount, render, shallow} from 'enzyme';
import { expect } from 'chai';

describe('User list', () => {
    const admin = {
        name: 'Thomas',
        surname: 'Black',
        department: {
            id: 1,
            name: 'Administration'
        },
        position: 'CEO',
        rate: 4.9,
        type: {
            id: 3
        }
    };
    const user = {
        name: 'Jeff',
        surname: 'Bolson',
        department: {
            id: 3,
            name: 'Logistic'
        },
        position: 'Driver',
        rate: 3.4,
        type: {
            id: 1
        }
    };
    const users = [admin, user];
    it('does not display empty table', () => {
        const wrapper = shallow(<UserList />);
        expect(wrapper).to.not.contain(<Table />);
    });

    const wrapper = shallow(<UserList users={users} currentUser={admin} />);
    const wrapper2 = shallow(<UserList users={users} currentUser={user} />);

    it('displays table with users', () => {
        expect(wrapper.find('tbody')).to.have.exactly(2).descendants('tr');
    });

    it('displays correct rate', () => {
        expect(wrapper.find('th').last()).to.have.text('Ocena');
        expect(wrapper2.find('th').last()).to.not.have.text('Ocena');
        expect(wrapper.find('tr')).to.have.exactly(2).descendants('.userRate');
        expect(wrapper2.find('tr')).to.have.exactly(0).descendants('.userRate');
        expect(wrapper.find('.userRate').first()).to.have.text('4.9');
        expect(wrapper.find('.userRate').last()).to.have.text('3.4');
    });

    it('displays correct data', () => {
        expect(wrapper.find(Link).first().childAt(0)).to.have.text('Thomas Black');
        expect(wrapper.find('tbody tr').first().find('td').at(1)).to.have.text('Administration');
        expect(wrapper.find('tbody tr').first().find('td').at(2)).to.have.text('CEO');
    });
});