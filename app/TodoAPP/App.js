import React, {useState} from 'react';
import {Alert, FlatList, StatusBar,StyleSheet,Text,View , TouchableWithoutFeedback , Keyboard} from 'react-native';
import Header from './components/header';
import TodoItem from './components/TodoItem';
import AddTodo from './components/AddTodo';
// import Sandbox from './components/sandbox';

export default function App(){
  const [todos, setTodos] = useState([
    { text: 'buy coffee', key: '1' },
    { text: 'create an app', key: '2' },
    { text: 'play on the switch', key: '3' }
  ]);

  const submitHandler = (text) =>{
    if (text.length > 3){
    setTodos((prevTodos)=>{
      return[
        {text : text, key: Math.random().toString()},
        ...prevTodos
      ]
    })
    }
    else
    {
      Alert.alert('Oops!', 'Todos must be over 3 chars long',[
        {
          text: 'Understood', onPress:()=> console.log('alert closed')
        }
      ]);
    }
  }
  const pressHandler = (key)=>{
    setTodos((prevTodos)=>{
      return prevTodos.filter(todo => todo.key != key);
    });
  }

  return(
  // <Sandbox/>
  <TouchableWithoutFeedback onPress={()=>{
    Keyboard.dismiss()
    console.log('dismissed keyboard')
  }}>
  <View style={styles.container}>
    <Header />
    <View style={styles.content}>
      <AddTodo submitHandler={submitHandler} />
      <View style={styles.list}>
        <FlatList 
        data={todos}
        renderItem={({item})=>(
          <TodoItem item={item} pressHandler={pressHandler} />
        )}/>
      </View>
    </View>
  </View>
  </TouchableWithoutFeedback>
 );
}

const styles=StyleSheet.create({
  container:{
    flex:1,
    backgroundColor:"fff",
  },
  content:{
    flex:1,
    padding:40,
  },
  list:{
    flex:1,
    marginTop:20,
  }
})