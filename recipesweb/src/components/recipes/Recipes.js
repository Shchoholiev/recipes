import React, { useEffect, useState } from 'react';
import RecipesService from './RecipesService';
import { Link, useNavigate } from "react-router-dom";
import Pagination from '../shared/Pagination';

export default function Recipes() {

    let navigate = useNavigate();

    const [recipes, setRecipes] = useState([]);
    const [pagesCount, setPagesCount] = useState([]);
    const [pageNumber, setPageNumber] = useState([]);

    useEffect(
        () => {
            getPage(1)
        }, []
    )

    const getPage = async (pageNumber) => {
        let response = await RecipesService.getPage(pageNumber);
        var data = await response.json();
        setRecipes(data.items);
        setPagesCount(data.pagesCount);
        setPageNumber(pageNumber);
    }

    const deleteRecipe = async (id) => {
        await RecipesService.delete(id);
        await getPage(pageNumber);
    }

    return (
        <article>
            <div className='d-flex justify-content-between mb-3'>
                <h3 className='m-0'>Recipes</h3>
                <Link to='/recipes/add' className='btn btn-primary'>Add Recipe</Link>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Thumbnail</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        recipes.map((recipe, index) => {
                            return (
                                <tr key={index}>
                                    <td onClick={() => navigate(`/recipes/${recipe.id}`)} style={{ cursor: "pointer" }}>
                                        <img src={recipe.thumbnail} style={{ height: "50px", width: "90px", objectFit: "cover" }}></img>
                                    </td>
                                    <td onClick={() => navigate(`/recipes/${recipe.id}`)} style={{ cursor: "pointer" }}>{recipe.name}</td>
                                    <td onClick={() => navigate(`/recipes/${recipe.id}`)} style={{ cursor: "pointer" }}>{recipe.category.name}</td>
                                    <td style={{ width: "1%", zIndex: 10 }} onClick={() => { navigate(`/recipes/edit/${recipe.id}`) }}>
                                        <button className='btn btn-primary'>Edit</button>
                                    </td>
                                    <td style={{ width: "1%" }} onClick={() => { deleteRecipe(recipe.id) }}>
                                        <button className='btn btn-danger'>Delete</button>
                                    </td>
                                </tr>
                            );
                        })
                    }
                </tbody>
            </table>
            <Pagination setPage={getPage} pagesCount={pagesCount} pageNumber={pageNumber}></Pagination>
        </article>
    );
}