void shuffleArray(int arr[], int n) {
  vector<int> v;
  int i = 0, mid = n / 2;
  
  while(i < mid and mid < n) {
      v.push_back(arr[i]);
      v.push_back(arr[mid]);
      i++, mid++;
  }
  
  for(int i = 0; i < n; i++)
      arr[i] = v[i];
}

// java always TLE