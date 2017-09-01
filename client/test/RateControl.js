import React from 'react';
import RateControl from '../src/js/components/profile/RateControl';
import Rate from 'rc-rate';
import {mount, render, shallow} from 'enzyme';
import { expect } from 'chai';

describe('Rate Control', () => {
    it('contains Rate element', () => {
        const wrapper = mount(<RateControl />);
        expect(wrapper).to.contain(<Rate />);
    });

    it('has correct rate value', () => {
        const rate = 5;
        const wrapper = mount(<RateControl value={rate} />);
        expect(wrapper.find(Rate)).to.have.prop('value', rate);
    });
});