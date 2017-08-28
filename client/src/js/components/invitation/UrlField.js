import React from 'react';
import { FormGroup, FormControl, HelpBlock } from 'react-bootstrap';

/**
 * Component of url field which allow to copy content automatically
 */
class UrlField extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            copied: false
        };
    }

    getUrlForHash(hash) {
        var url = location.href.substr(0, location.href.indexOf('#')+1);
        return url + '/register/' + hash;
    }

    render() {
        let style = {
            display: this.state.copied?'block':'none'
        };
        return (
            <FormGroup validationState={this.state.copied?'success':null}>
                <FormControl
                    type="text"
                    value={this.getUrlForHash(this.props.hash)}
                    onChange={(e) => {
                        e.preventDefault();
                        return false;
                    }}
                    onClick={(e) => {
                        e.target.select();
                        document.execCommand('copy');
                        e.target.focus();
                        this.setState({ copied: true });
                }}
                />
                <HelpBlock style={style}>Skopiowano</HelpBlock>
            </FormGroup>
        );
    }
}

export default UrlField;