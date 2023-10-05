fn main() {
    let set = [3, 34, 4, 12, 5, 2];
    let sum = 9;
    println!("{:?}", subset_sum(&set, sum, set.len()));

    let sum = 30;
    println!("{:?}", subset_sum(&set, sum, set.len()));
}

fn subset_sum(set: &[usize], sum: usize, n: usize) -> Vec<usize> {
    if sum == 0 {
        return vec![set[n]];
    }
    if n == 0 {
        return vec![];
    }

    if set[n - 1] > sum {
        subset_sum(set, sum, n - 1)
    } else {
        let mut positive_case = subset_sum(set, sum - set[n - 1], n - 1);

        // if positive case is good return that
        // if not return whatever negative case is
        let pos_len = positive_case.len();
        if pos_len != 0 {
            if pos_len != 1 || positive_case[0] != set[n - 1] {
                positive_case.push(set[n - 1]);
            }
            positive_case
        } else {
            subset_sum(set, sum, n - 1)
        }
    }
}

fn subset_temp(set: &[usize], sum:usize) -> bool {
    subset_sum_boolean(set, sum, set.len() - 1)
}

fn subset_sum_boolean(set: &[usize], sum: usize, n: usize) -> bool {
    if sum == 0 {
        return true;
    }
    if n == 0 {
        return false;
    }

    if set[n] > sum {
        return subset_sum_boolean(set, sum, n - 1);
    }
    subset_sum_boolean(set, sum - set[n], n - 1) || subset_sum_boolean(set, sum, n - 1)
}
