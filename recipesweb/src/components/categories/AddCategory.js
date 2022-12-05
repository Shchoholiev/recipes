import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import CategoriesService from './CategoriesService';

export default function AddCategory() {

    let navigate = useNavigate();

    let { id } = useParams();

    let isUpdate = id > 0;

    const { register, handleSubmit, reset, setValue, getValues, errors, formState } = useForm({});

    const [category, setCategory] = useState([]);

    useEffect(() => {
        if (isUpdate) {
            CategoriesService.getCategory(id).then(
                response => {
                    response.json().then(
                        data => {
                            setCategory(data);
                            const fields = ['name'];
                            fields.forEach(field => setValue(field, data[field]));
                        }
                    )
                }
            );
        }
    }, [])

    const onSubmit = async (data) => {
        if (isUpdate) {
            await CategoriesService.update(id, data);
            navigate(`/categories`);
        } else {
            await CategoriesService.add(data);
            navigate("/categories");
        }
    }

    return (
        <section className='mb-5'>
            <form onSubmit={handleSubmit(onSubmit)} className='d-flex flex-column'>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" {...register('name', { required: true })} />
                </div>
                <button type="submit" className="btn btn-success mx-auto">{isUpdate ? "Update" : "Add Category"}</button>
            </form>
        </section>
    );
}