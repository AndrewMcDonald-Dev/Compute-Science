use std::fmt::Display;

fn main() {
    let mut matrix: Matrix<isize> = Matrix::new();
    matrix.fill(14, 10, 10);
    println!("{}", matrix);
}

struct Matrix<T> {
    data: Vec<Vec<T>>,
}

impl<T: Clone> Matrix<T> {
    fn new() -> Self {
        Matrix {
            data: vec![vec![]; 0],
        }
    }
}

impl<T: Display + Copy> Matrix<T> {
    pub fn fill(&mut self, scalar: T, dim_x: usize, dim_y: usize) {
        self.data = (0..dim_y).map(|_| vec![scalar; dim_x]).collect();
    }
}

impl<T: Display> Display for Matrix<T> {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
            "{}",
            self.data.iter().fold("".to_owned(), |acc: String, vec| {
                acc + &vec
                    .iter()
                    .fold("│ ".to_string(), |acc, x| format!("{}{: ^4}", acc, x))
                    + " │\n"
            })
        )
    }
}
