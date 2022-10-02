import React, {useState} from 'react';
import {StyleSheet,Text,View,TextInput,Button} from 'react-native';


export default function AddTodo({submitHandler}){

    const [text,setText]=useState('');

    const changeHandler=(val)=>{
        setText(val);
    }


    return(
        <View style={styles.header}>
            <TextInput 
            style={styles.input}
            placeholder='New Todo...'
            onChangeText={changeHandler}/>
            <View style={styles.designBtn}>
            <Button onPress={()=> submitHandler(text) } title='ADD Todo' color='white'/>
            </View>
        </View>
    );
}

const styles=StyleSheet.create({
    input:{
        marging:10,
        paddingHorizontal: 8,
        paddingVertical: 6,
        borderBottomWidth:1,
        borderBottomColor:"#ddd"
    },
    designBtn:{
        marginTop:10,
        backgroundColor:'grey',
        borderRadius:8
    }
})