use std::{fs, io::Write, ops::Add};
extern crate num;

#[derive(Debug, Clone)]
struct Car {
    symboling: isize,
    normalized_losses: Option<isize>,
    make: String,
    fuel_type: Option<String>,
    aspiration: String,
    num_of_doors: Option<usize>,
    body_style: Option<String>,
    drive_wheels: Option<String>,
    engine_location: Option<String>,
    wheel_base: Option<f32>,
    length: f32,
    width: f32,
    height: f32,
    curb_weight: Option<usize>,
    engine_type: Option<String>,
    num_of_cylinders: Option<usize>,
    engine_size: Option<usize>,
    fuel_system: Option<String>,
    bore: Option<f32>,
    stroke: Option<f32>,
    compression_ratio: Option<f32>,
    horsepower: String,
    peak_rpm: Option<usize>,
    city_mpg: Option<usize>,
    highway_mpg: Option<usize>,
    price: Option<usize>,
}

impl Car {
    fn from_arr(vec: Vec<&str>) -> Car {
        let fuel_type = Car::parse_string(vec[4]);
        let body_style = Car::parse_string(vec[7]);
        let drive_wheels = Car::parse_string(vec[8]);
        let engine_location = Car::parse_string(vec[9]);
        let engine_type = Car::parse_string(vec[15]);
        let fuel_system = Car::parse_string(vec[18]);
        Car {
            symboling: vec[1].parse::<isize>().unwrap(),
            normalized_losses: vec[2].parse::<isize>().ok(),
            make: vec[3].to_string(),
            fuel_type,
            aspiration: vec[5].to_string(),
            num_of_doors: match vec[6] {
                "two" => Some(2),
                "four" => Some(4),
                _ => None,
            },
            body_style,
            drive_wheels,
            engine_location,
            wheel_base: vec[10].parse().ok(),
            length: vec[11].parse().unwrap(),
            width: vec[12].parse().unwrap(),
            height: vec[13].parse().unwrap(),
            curb_weight: vec[14].parse().ok(),
            engine_type,
            num_of_cylinders: match vec[16] {
                "two" => Some(2),
                "three" => Some(3),
                "four" => Some(4),
                "five" => Some(5),
                "six" => Some(6),
                "eight" => Some(8),
                "twelve" => Some(12),
                _ => None,
            },
            engine_size: vec[17].parse().ok(),
            fuel_system,
            bore: vec[19].parse().ok(),
            stroke: vec[20].parse().ok(),
            compression_ratio: vec[21].parse().ok(),
            horsepower: vec[22].to_string(),
            peak_rpm: vec[23].parse().ok(),
            city_mpg: vec[24].parse().ok(),
            highway_mpg: vec[25].parse().ok(),
            price: vec[26].parse().ok(),
        }
    }

    fn parse_string(s: &str) -> Option<String> {
        let mut attri = Some(s.to_string());
        if attri == Some("?".to_string()) {
            None
        } else {
            attri
        }
    }

    fn add_mean<T>(car_attri: Option<T>, mut mean: (T, T)) -> (T, T)
    where
        T: num::One + Add<T, Output = T>,
    {
        if let Some(num) = car_attri {
            mean = (mean.0 + num, mean.1 + T::one())
        }
        mean
    }

    fn replace_with_mean<T>(car_attri: Option<T>, mean: T) -> Option<T> {
        if car_attri.is_none() {
            Some(mean)
        } else {
            car_attri
        }
    }

    fn clean_data(cars: Vec<Car>) -> Vec<Car> {
        let mut new_cars = cars.clone();
        // I am going to use a tuble to scalculate the mean for every numeric attribute
        let mut losses_mean = (0, 0);
        let mut doors_mean = (0, 0);
        let mut wheel_base_mean = (0.0, 0.0);
        let mut curb_weight_mean: (usize, usize) = (0, 0);
        let mut cylinders_mean = (0, 0);
        let mut engine_size_mean = (0, 0);
        let mut bore_mean = (0.0, 0.0);
        let mut stroke_mean = (0.0, 0.0);
        let mut compression_mean = (0.0, 0.0);
        let mut city_mpg_mean = (0, 0);
        let mut rpm_mean = (0, 0);
        let mut highway_mpg_mean = (0, 0);
        let mut price_mean = (0, 0);

        // Gatherng mean data
        for car in cars.iter() {
            losses_mean = Car::add_mean(car.normalized_losses, losses_mean);
            doors_mean = Car::add_mean(car.num_of_doors, doors_mean);
            wheel_base_mean = Car::add_mean(car.wheel_base, wheel_base_mean);
            curb_weight_mean = Car::add_mean(car.curb_weight, curb_weight_mean);
            cylinders_mean = Car::add_mean(car.num_of_cylinders, cylinders_mean);
            engine_size_mean = Car::add_mean(car.engine_size, engine_size_mean);
            bore_mean = Car::add_mean(car.bore, bore_mean);
            stroke_mean = Car::add_mean(car.stroke, stroke_mean);
            compression_mean = Car::add_mean(car.compression_ratio, compression_mean);
            city_mpg_mean = Car::add_mean(car.city_mpg, city_mpg_mean);
            rpm_mean = Car::add_mean(car.peak_rpm, rpm_mean);
            highway_mpg_mean = Car::add_mean(car.highway_mpg, highway_mpg_mean);
            price_mean = Car::add_mean(car.price, price_mean);
        }

        //Calculating mean
        let losses_mean = losses_mean.0 / losses_mean.1;
        let doors_mean = doors_mean.0 / doors_mean.1;
        let wheel_base_mean = wheel_base_mean.0 / wheel_base_mean.1;
        let curb_weight_mean = curb_weight_mean.0 / curb_weight_mean.1;
        let cylinders_mean = cylinders_mean.0 / cylinders_mean.1;
        let engine_size_mean = engine_size_mean.0 / engine_size_mean.1;
        let bore_mean = bore_mean.0 / bore_mean.1;
        let stroke_mean = stroke_mean.0 / stroke_mean.1;
        let compression_mean = compression_mean.0 / compression_mean.1;
        let city_mpg_mean = city_mpg_mean.0 / city_mpg_mean.1;
        let rpm_mean = rpm_mean.0 / rpm_mean.1;
        let highway_mpg_mean = highway_mpg_mean.0 / highway_mpg_mean.1;
        let price_mean = price_mean.0 / price_mean.1;

        //Filling blank data with mean
        for car in new_cars.iter_mut() {
            car.normalized_losses = Car::replace_with_mean(car.normalized_losses, losses_mean);
            car.num_of_doors = Car::replace_with_mean(car.num_of_doors, doors_mean);
            car.wheel_base = Car::replace_with_mean(car.wheel_base, wheel_base_mean);
            car.curb_weight = Car::replace_with_mean(car.curb_weight, curb_weight_mean);
            car.num_of_cylinders = Car::replace_with_mean(car.num_of_cylinders, cylinders_mean);
            car.engine_size = Car::replace_with_mean(car.engine_size, engine_size_mean);
            car.bore = Car::replace_with_mean(car.bore, bore_mean);
            car.stroke = Car::replace_with_mean(car.stroke, stroke_mean);
            car.compression_ratio = Car::replace_with_mean(car.compression_ratio, compression_mean);
            car.city_mpg = Car::replace_with_mean(car.city_mpg, city_mpg_mean);
            car.peak_rpm = Car::replace_with_mean(car.peak_rpm, rpm_mean);
            car.highway_mpg = Car::replace_with_mean(car.highway_mpg, highway_mpg_mean);
            car.price = Car::replace_with_mean(car.price, price_mean);
        }

        new_cars
    }
}

fn main() {
    let content = fs::read_to_string("cars.csv").expect("Could not find file.");

    let mut records = vec![];
    for (i, result) in content.split("\n").enumerate() {
        // Skip bad entries
        if i == 0 || i == 206 {
            continue;
        }

        // Grab unparsed array of record
        let record: Vec<&str> = result.split(",").collect();
        //Parse arr into struct Car
        let record: Car = Car::from_arr(record);
        records.push(record)
    }
    println!("{:?}", records[2]);
    std::io::stdout().flush().unwrap();

    //Fill in missing numeric values with the mean of that column
    records = Car::clean_data(records);

    println!("{:?}", records[2]);
    println!("{}", records.len())
}
