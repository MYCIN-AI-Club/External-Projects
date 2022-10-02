import React from 'react'
import {StyleSheet, TouchableOpacity, Text, View,Image} from 'react-native'

export default function TodoItem({ pressHandler, item }) {
  return (
    <TouchableOpacity onPress={() => pressHandler(item.key)}>
        {/* <View style={styles.item}> */}
        <Text style={styles.item}>{item.text}</Text>
        {/* </View> */}
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  item: {
    padding: 16,
    marginTop: 16,
    borderColor: '#bbb',
    borderWidth: 1,
    borderStyle: "dashed",
    borderRadius: 1,
    borderRadius: 10,
  }
});