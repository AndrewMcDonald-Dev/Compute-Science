use rand::prelude::*;
fn main() {
    initial_formatting();
    for n in [1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000] {
        //initialize random array with size i
        let mut test_arr: Vec<i32> = Vec::with_capacity(n);
        for _ in 0..n {
            test_arr.push(rand::thread_rng().gen_range(0..100000));
        }
        let mut insertion_test = test_arr.clone();
        let mut quick_test = test_arr.clone();
        let mut merge_test = test_arr.clone();
        let mut heap_test = test_arr.clone();
        let comp_insert = insertion_sort(&mut insertion_test);
        let comp_quick = quick_sort(&mut quick_test);
        let (comp_merge, _) = mergesort(&mut merge_test);
        let comp_heap = heapsort(&mut heap_test);
        let is_end = n == 10000;
        output_formatter(
            [
                n,
                comp_insert,
                comp_quick,
                comp_merge,
                comp_heap,
                (n as f64 * (n as f64).log2()) as usize,
            ],
            is_end,
        )
    }

    // let mut test_arr = [0, 3, 2, 5, 6, 9, 1, 8, 7, 4];
    // let comp = quick_sort(&mut test_arr);
    // println!("{}, {:?}", comp, test_arr);
}

fn output_formatter(results: [usize; 6], is_end: bool) {
    println!(
        "│ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │",
        results[0], results[1], results[2], results[3], results[4], results[5]
    );
    if !is_end {
        println!(
            "├{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┤",
            "", "", "", "", "", ""
        );
    } else {
        println!(
            "└{:─<11}┴{:─<11}┴{:─<11}┴{:─<11}┴{:─<11}┴{:─<11}┘",
            "", "", "", "", "", ""
        )
    }
}

fn initial_formatting() {
    println!(
        "┌{:─<11}┬{:─<11}┬{:─<11}┬{:─<11}┬{:─<11}┬{:─<11}┐",
        "", "", "", "", "", ""
    );
    println!(
        "│ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │ {: ^9} │",
        "N", "Insertion", "Quick", "Merge", "Heap", "NlgN"
    );
    println!(
        "├{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┼{:─<11}┤",
        "", "", "", "", "", ""
    );
}

fn insertion_sort(arr: &mut Vec<i32>) -> usize {
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

// fn quick_sort(values: &mut [i32], start: Option<isize>, end: Option<isize>) -> usize {
//     fn partition(arr: &mut [i32], start: isize, end: isize) -> (usize, usize) {
//         let mut count = 0;
//         let pivot = end as usize;
//         let mut store_index = start - 1;
//         let mut last_index = end;
//         loop {
//             store_index += 1;
//             while arr[store_index as usize] < arr[pivot] {
//                 store_index += 1;
//                 count += 1;
//             }

//             last_index -= 1;
//             while last_index >= 0 && arr[last_index as usize] > arr[pivot] {
//                 count += 1;
//                 last_index -= 1;
//             }
//             if store_index >= last_index {
//                 break;
//             } else {
//                 arr.swap(store_index as usize, last_index as usize);
//             }
//         }
//         arr.swap(store_index as usize, pivot as usize);
//         println!("test: {}", count);
//         (store_index as usize, count)
//     }
//     let mut count = 0;

//     let start_val = start.unwrap_or(0);
//     let end_val = end.unwrap_or((values.len() - 1) as isize);
//     let mut test = false;

//     // println!("test: {}", count);
//     if start_val < end_val {
//         let (pivot, new_count) = partition(values, start_val, end_val);
//         count += new_count;
//         count += quick_sort(values, Some(start_val), Some(pivot as isize - 1));
//         count += quick_sort(values, Some(pivot as isize + 1), Some(end_val));
//         test = true;
//     }
//     println!("{} {}", count, test);
//     count
// }
fn quick_sort(slice: &mut [i32]) -> usize {
    fn partition(slice: &mut [i32]) -> (usize, usize) {
        let len = slice.len();
        let pivot = slice[len - 1];
        let mut i = 0;
        let mut j = 0;
        let mut count = 0;

        while j < len - 1 {
            if slice[j] <= pivot {
                slice.swap(i, j);
                i += 1;
            }
            count += 1;
            j += 1;
        }

        slice.swap(i, len - 1);

        (i, count)
    }
    let mut count = 1;
    if !slice.is_empty() {
        let (partition_index, new_count) = partition(slice);
        let len = slice.len();
        count += new_count;

        count += quick_sort(&mut slice[0..partition_index]);
        count += quick_sort(&mut slice[partition_index + 1..len]);
    }
    count + 1
}

fn shell_sort(arr: &mut [i32]) {
    let len = arr.len();
    let mut gap = len as i32 / 2;

    while gap > 0 {
        for i in gap..len as i32 {
            let temp = arr[i as usize];
            let mut j = i;

            while j >= gap && arr[j as usize - gap as usize] > temp {
                arr.swap(j as usize, j as usize - gap as usize);
                j -= gap;
            }

            arr[j as usize] = temp;
        }
        gap /= 2;
    }
}

fn heapsort(arr: &mut Vec<i32>) -> usize {
    fn sift_down(arr: &mut [i32], mut root: usize) -> usize {
        let mut count = 0;
        let end = arr.len() - 1;
        loop {
            let left = 2 * root + 1;
            if left > end {
                break;
            }

            let right = left + 1;
            let max = if right <= end && arr[right] > arr[left] {
                right
            } else {
                left
            };
            count += 1;

            if arr[max] > arr[root] {
                arr.swap(root, max);
                count += 1;
            }
            root = max;
        }
        count
    }
    let mut count = 0;

    for start in (0..arr.len() / 2).rev() {
        // Skip leaf nodes (end / 2).
        count += sift_down(arr, start);
    }

    for end in (1..arr.len()).rev() {
        arr.swap(end, 0);
        count += sift_down(&mut arr[..end], 0);
    }
    count
}

fn mergesort(arr: &mut Vec<i32>) -> (usize, Vec<i32>) {
    fn merge(arr1: &[i32], arr2: &[i32]) -> (usize, Vec<i32>) {
        let mut count = 0;
        let mut left = 0; // Head of left pile.
        let mut right = 0; // Head of right pile.
        let mut merged: Vec<i32> = Vec::new();
        while left < arr1.len() && right < arr2.len() {
            if arr1[left] <= arr2[right] {
                merged.push(arr1[left]);
                left += 1;
            } else {
                merged.push(arr2[right]);
                right += 1;
            }
            count += 1;
        }

        while left < arr1.len() {
            merged.push(arr1[left]);
            left += 1;
        }
        while right < arr2.len() {
            merged.push(arr2[right]);
            right += 1;
        }

        (count, merged)
    }

    let mut count = 0;

    let n = arr.len();
    let m = n / 2;
    if n <= 1 {
        return (count, arr.to_vec());
    }

    let (new_count1, left) = mergesort(&mut arr[..m].to_vec());
    let (new_count2, right) = mergesort(&mut arr[m..].to_vec());

    let (new_count3, merged) = merge(&left, &right);
    count += new_count1 + new_count2 + new_count3;

    (count, merged)
}
