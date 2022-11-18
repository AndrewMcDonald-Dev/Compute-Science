fn main() {
    let test_arr = [4, 1, 2, 8, 7, 3, 5, 6, 9, 0];
    let mut insertion_test = test_arr.clone();
    let mut quick_test = test_arr.clone();
    let mut merge_test = test_arr.clone();
    let mut heap_test = test_arr.clone();
    let comp_insert = insertion_sort(&mut insertion_test);
    let comp_quick = quick_sort(&mut quick_test, None, None);
    let comp_merge = mergesort(&mut merge_test);
    let comp_heap = heapsort(&mut heap_test);

    println!(
        "Comparisons: {}, Sorted-Arr: {:?}",
        comp_insert, insertion_test
    );
    println!("Comparisons: {}, Sorted-Arr: {:?}", comp_quick, quick_test);
    println!("Comparisons: {}, Sorted-Arr: {:?}", comp_merge, merge_test);
    println!("Comparisons: {}, Sorted-Arr: {:?}", comp_heap, heap_test);
}

fn insertion_sort(arr: &mut [i32]) -> usize {
    let mut count = 0;
    for i in 1..arr.len() {
        let mut j = i;
        while j > 0 && arr[j - 1] > arr[j] {
            arr.swap(j - 1, j);
            count += 1;
            j -= 1;
        }
    }
    count
}

fn quick_sort(values: &mut [i32], start: Option<isize>, end: Option<isize>) -> usize {
    fn partition(partition_values: &mut [i32], start: isize, end: isize) -> (isize, usize) {
        let mut count = 0;
        let mut pivot = start;
        for idx in (start + 1)..(end + 1) {
            if partition_values[idx as usize] <= partition_values[start as usize] {
                count += 1;
                pivot += 1;
                let tmp = partition_values[idx as usize];
                partition_values[idx as usize] = partition_values[pivot as usize];
                partition_values[pivot as usize] = tmp;
            }
        }
        let tmp = partition_values[start as usize];
        partition_values[start as usize] = partition_values[pivot as usize];
        partition_values[pivot as usize] = tmp;
        return (pivot, count);
    }
    let mut count = 0;

    let start_val = start.unwrap_or(0);
    let end_val = end.unwrap_or((values.len() - 1) as isize);
    if start_val >= end_val {
        return count;
    }
    let (pivot, new_count) = partition(values, start_val, end_val);
    count += new_count;
    count += quick_sort(values, Some(start_val), Some(pivot - 1));
    count += quick_sort(values, Some(pivot + 1), Some(end_val));
    count
}

fn heapsort(arr: &mut [i32]) -> usize {
    fn sift_down(arr: &mut [i32], start: usize, end: usize) -> usize {
        let mut count = 0;
        let mut root = start;
        loop {
            let mut child = root * 2 + 1; // Get the left child
            if child > end {
                break;
            }
            // Right child exists and is greater.
            if child < end && arr[child] < arr[child + 1] {
                count += 1;
                child += 1;
            }

            // If child is greater than root, swap'em!
            if arr[root] < arr[child] {
                count += 1;
                arr.swap(root, child);
                root = child;
            } else {
                break;
            }
        }
        count
    }
    let mut count = 0;

    let end = arr.len();
    for start in (0..end / 2).rev() {
        // Skip leaf nodes (end / 2).
        count += sift_down(arr, start, end - 1);
    }

    for end in (1..arr.len()).rev() {
        arr.swap(end, 0);
        count += sift_down(arr, 0, end - 1);
    }
    count
}

fn mergesort(arr: &mut [i32]) -> usize {
    fn merge(arr1: &[i32], arr2: &[i32], ret: &mut [i32]) -> usize {
        let mut count = 0;
        let mut left = 0; // Head of left pile.
        let mut right = 0; // Head of right pile.
        let mut index = 0;

        while left < arr1.len() && right < arr2.len() {
            if arr1[left] <= arr2[right] {
                ret[index] = arr1[left];
                count += 1;
                index += 1;
                left += 1;
            } else {
                ret[index] = arr2[right];
                count += 1;
                index += 1;
                right += 1;
            }
        }

        if left < arr1.len() {
            ret[index..].copy_from_slice(&arr1[left..]);
        }
        if right < arr2.len() {
            ret[index..].copy_from_slice(&arr2[right..]);
        }
        count
    }

    let mut count = 0;

    let mid = arr.len() / 2;
    if mid == 0 {
        return count;
    }

    count += mergesort(&mut arr[..mid]);
    count += mergesort(&mut arr[mid..]);

    let mut ret = arr.to_vec();

    count += merge(&arr[..mid], &arr[mid..], &mut ret[..]);

    arr.copy_from_slice(&ret);
    count
}
