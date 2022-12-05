import React, { useEffect, useState } from 'react';
import RecipesService from './RecipesService';
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function RecipeDetails() {

    let navigate = useNavigate();

    let { id } = useParams();

    const [recipe, setRecipe] = useState([]);

    useEffect(
        () => {
            getRecipe()
        }, []
    )

    const getRecipe = async () => {
        let response = await RecipesService.getRecipe(id);
        var data = await response.json();
        setRecipe(data);
    }

    const deleteRecipe = async () => {
        await RecipesService.delete(id); 
        navigate("/recipes")
    }

    return (
        <section>
            <div className='mb-5'>
                <div className='d-flex justify-content-between align-items-center gap-3'>
                    <div>
                        <h6 className='fw-bold text-primary' style={{ marginBottom: "-7px" }}>NAME</h6>
                        <h1 className='m-0'>{recipe.name}</h1>
                    </div>
                    <div className='d-flex gap-2'>
                        <button className='btn btn-primary' onClick={() => { navigate(`/recipes/edit/${id}`) }}>Edit</button>
                        <button className='btn btn-danger' onClick={ () => deleteRecipe() }>Delete</button>
                    </div>
                </div>
                <img src={recipe.thumbnail} className='my-3' style={{ height: "450px", width: "100%", objectFit: "cover" }}></img>
                <h3 className='fw-bold text-secondary'>Ingredients</h3>
                <p>{recipe.ingredients}</p>
                <h3 className='fw-bold text-secondary'>Recipe</h3>
                <p>{recipe.text}</p>
            </div>
        </section>
    );
}