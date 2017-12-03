import React, { Component } from 'react';

import PropTypes from 'prop-types';

import {requireNativeComponent,View,UIManager,findNodeHandle} from 'react-native';

export default class WalkView extends Component{
  render(){
    return (
      <RCTAMapViewTest
                {...this.props}
            />
        );
    };
}

WalkView.name = "WalkView";
WalkView.propTypes = {
    test:PropTypes.string.isRequired,
    ...View.propTypes,
}

var RCTAMapViewTest = requireNativeComponent('WalkView',WalkView);