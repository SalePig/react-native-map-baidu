import React, { Component } from 'react';

import PropTypes from 'prop-types';

import {requireNativeComponent,View,UIManager,findNodeHandle} from 'react-native';

export default class MapView extends Component{
  render(){
    return (
      <RCTAMapViewTest
                {...this.props}
            />
        );
    };
}

MapView.name = "MapView";
MapView.propTypes = {
    test:PropTypes.string.isRequired,
    ...View.propTypes,
}

var RCTAMapViewTest = requireNativeComponent('MapView',MapView);