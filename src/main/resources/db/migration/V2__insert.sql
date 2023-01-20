INSERT INTO public.car_marks(mark_id, mark_name)
VALUES (1, 'BMW'),
       (2, 'ВАЗ');

INSERT INTO public.regions(region_id, region_number)
VALUES (1, 99),
       (2, 177),
       (3, 96);

INSERT INTO public.car_models(model_id, model_name, car_mark_id)
VALUES (1, '2109', 2),
       (2, '2110', 2),
       (3, '2111', 2),
       (4, 'X5', 1),
       (5, '5 серия', 1);

INSERT INTO public.cars(amount_of_owners, body_type, car_number, color, date_added, mileage, year, car_mark_id,
                        car_model_id, region_id)
VALUES
    (3, 'SEDAN', 'А899НХ', 'чёрный',current_timestamp, 100000, '2002', 2, 1, 3),
    (5, 'SEDAN', 'А001СС', 'белый', current_timestamp, 200000, '2000', 2, 2, 1),
    (3, 'SEDAN', 'АН001Х', 'синий', current_timestamp, 80000, '2010', 1, 5, 2),
    (2, 'OFF_ROAD', 'С777УХ', 'красный', current_timestamp, 70000, '2012', 1, 4, 1);