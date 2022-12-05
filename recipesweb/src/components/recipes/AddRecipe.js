import React, { useEffect, useState } from 'react';
import RecipesService from './RecipesService';
import { useParams } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import CategoriesAddPanel from '../categories/CategoriesAddPanel';
import CloudStorageService from '../shared/CloudStorageService';

export default function AddRecipe() {

    let navigate = useNavigate();

    let { id } = useParams();

    let isUpdate = id > 0;

    const { register, handleSubmit, reset, setValue, getValues, errors, formState } = useForm({});

    const [recipe, setRecipe] = useState([]);
    const [category, setCategory] = useState([]);
    const [thumbnail, setThumbnailVariable] = useState([]);

    useEffect(() => {
        if (isUpdate) {
            RecipesService.getRecipe(id).then(
                response => {
                    response.json().then(
                        data => {
                            setRecipe(data);
                            const fields = ['name', 'thumbnail', 'ingredients', 'text'];
                            setValue("category.id", data.category.id);
                            setValue("category.name", data.category.name);
                            setThumbnailVariable(data.thumbnail);
                            fields.forEach(field => setValue(field, data[field]));
                            setCategory(data.category);
                            var input = document.getElementById('thumbnailLink');
                            input.value = data.thumbnail;
                        }
                    )
                }
            );
        }
    }, [])

    const onSubmit = async (data) => {
        if (isUpdate) {
            await RecipesService.update(id, data);
            navigate(`/recipes/${id}`);
        } else {
            await RecipesService.add(data);
            navigate("/recipes");
        }
    }

    const chooseCategory = (category) => {
        setCategory(category);
        setValue("category.id", category.id);
        setValue("category.name", category.name);
    }

    const setThumbnail = async (event) => {
        if (event.target.files != null && event.target.files[0] != null) {
            var file = event.target.files[0];
            var response = await CloudStorageService.upload("recipes-photos", file);
            var data = await response.json();
            setValue("thumbnail", data.link);
            setThumbnailVariable(data.link);
            var input = document.getElementById('thumbnailLink');
            input.value = data.link;
        }
    }

    const displayThumbnail = (event) => {
        setValue("thumbnail", event.target.value);
        setThumbnailVariable(event.target.value);
    }

    return (
        <section className='d-flex gap-3 mb-5'>
            <form onSubmit={handleSubmit(onSubmit)} className='d-flex flex-column' style={{flex: 6}}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" {...register('name', { required: true })} />
                </div>
                <div className='d-flex gap-5 align-items-center'>
                    <div className="mb-3" style={{flex: 1}}>
                        <label htmlFor="thumbnail" className="form-label">Thumbnail Link</label>
                        <input type="text" className="form-control" id="thumbnailLink" onBlur={ displayThumbnail }/>
                        <input hidden type="text" className="form-control" id="thumbnail" {...register('thumbnail', { required: true })} />
                    </div>
                    <h6 className='text-secondary m-0 mt-3'>OR</h6>
                    <div className="mb-3" style={{flex: 1}}>
                        <label htmlFor="thumbnail" className="form-label">Thumbnail File</label>
                        <input type="file" className="form-control" id="thumbnailFile" onChange={ setThumbnail } />
                    </div>
                </div>
                <div className="mb-3">
                    <img src={ thumbnail } style={{height: "300px", width: "100%", objectFit: "cover"}}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="ingredients" className="form-label">Ingredients</label>
                    <textarea rows={5} type="text" className="form-control" id="ingredients" {...register('ingredients', { required: true })}></textarea>
                </div>
                <div className="mb-3">
                    <label htmlFor="text" className="form-label">Recipe</label>
                    <textarea rows={5} type="text" className="form-control" id="text" {...register('text', { required: true })}></textarea>
                </div>
                <div className='d-flex flex-column gap-3'>
                    <div className="d-flex gap-3 align-items-center">
                        <label htmlFor="category" className="form-label">Category</label>
                        <hr className='m-0' style={{flex: 1}}></hr>
                    </div>
                    <CategoriesAddPanel chooseCategory={chooseCategory} chosenCategory={category} ></CategoriesAddPanel>
                </div>
                <hr></hr>
                <input hidden type="number" className="form-control" id="categoryId" {...register('category.id', { required: true })} />
                <button type="submit" className="btn btn-success mx-auto">{isUpdate ? "Update" : "Add Recipe"}</button>
            </form>
        </section>
    );
}