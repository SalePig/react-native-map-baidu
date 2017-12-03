/* eslint-disable react/jsx-filename-extension,react/prefer-stateless-function,no-use-before-define */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
} from 'react-native';

import WalkView from './src/component/WalkView'

export default class App extends Component<{}> {
  render() {
    return (
      <View style={styles.container}>
          <View style={styles.viewStyle}>

            <WalkView style={styles.mapStyle} test={"qiu"}/>

        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    videoStyle: {
        height: 250,
        width: 380,
    },

    viewStyle: {
        flex: 1,
        width:380,
        height:300,
        backgroundColor:'#ff0',
    },

    mapStyle: {
        flex: 1,
        // height: 380,
        // width: 380,
    },
});

