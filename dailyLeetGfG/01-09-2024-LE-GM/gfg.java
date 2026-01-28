public int maxPathSum(List<Integer> arr1, List<Integer> arr2) {
    // code here
    int i=0;
    int j=0;
    int a1=0;
    int a2=0;
    int max=0;
    int total=arr1.size()+arr2.size();
    for(int x=0;x<total;x++){
        if(i>=arr1.size() || j>=arr2.size()) break;
        if(arr1.get(i)<arr2.get(j)){
            a1+=arr1.get(i);
            i++;
        }
        else if(arr2.get(j)<arr1.get(i)){
            a2+=arr2.get(j);
            j++;
        }
        else{
            a1+=arr1.get(i);
            a2+=arr2.get(j);
            max=Math.max(max+a1,max+a2);
            a1=0;
            a2=0;
            i++;
            j++;
        }
    }
    for(int x=i;x<arr1.size();x++){
        a1+=arr1.get(x);
    }
    for(int y=j;y<arr2.size();y++){
        a2+=arr2.get(y);
    }
    max=Math.max(a1+max,a2+max);
    return max;
}