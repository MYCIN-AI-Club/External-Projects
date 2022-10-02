import React from 'react';
import {StyleSheet,Text,View,} from 'react-native';

export default function Sandbox(){
    return(
        <View style={styles.container}>
            <Text style={styles.boxOne}>one</Text>
            <Text style={styles.boxTwo}>Two</Text>
            <Text style={styles.boxThree}>Three</Text>
            <Text style={styles.boxFour}>Four</Text>
        </View>
    )
}

const styles=StyleSheet.create({
    container:{
        flex:1,
        paddingTop:40,
        backgroundColor:'#ddd',
    },
    boxOne:{
        padding:10,
        backgroundColor:'grey',
    },
    boxTwo:{
        padding:10,
        backgroundColor:'purple',
    },
    boxThree:{
        padding:10,
        backgroundColor:'red',
    },
    boxFour:{
        padding:10,
        backgroundColor:'violet',
    }
})