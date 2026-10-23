public E findMedian() {
    int sum = 0; 
    int length = data.length;
    if (length % 2 == 1){
        
    }
    for (int j = 0; j < data.length; j++){
        sum += data[i];
    }
}

Arrays.sort(data)

public E finddLast(){
    if (data == null){
        return null; //OR throw exception
    }
    return data[data.length - 1];
}
public E removeFirst(){
    E first = data[0];
    E[] copyData = (E[]) new Object(data.length - 1);
    for (int k = 1; k < data.length; k++){
        copyData[k -1] = data[k];
    }
    data = copyData;
    return first;
}
public String toString(){
    
}