func findLucky(arr []int) int {
	m := make(map[int]int)
	for i := 0; i < len(arr); i++ {
		m[arr[i]]++
	}
	maxLucky := -1
	for k, v := range m {
		if k == v {
			maxLucky = max(maxLucky, k)
		}
	}

	return maxLucky
}