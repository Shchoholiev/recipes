import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import Pagination from '../shared/Pagination';
import CategoriesService from './CategoriesService';

export default function Categories() {

    let navigate = useNavigate();

    const [categories, setCategories] = useState([]);
    const [pagesCount, setPagesCount] = useState([]);
    const [pageNumber, setPageNumber] = useState([]);

    useEffect(
        () => {
            getPage(1)
        }, []
    )

    const getPage = async (pageNumber) => {
        let response = await CategoriesService.getPage(pageNumber);
        let data = await response.json();
        setCategories(data.items);
        setPagesCount(data.pagesCount);
        setPageNumber(pageNumber);
    }

    const deleteCategory = async (id) => {
        await CategoriesService.delete(id);
        await getPage(pageNumber);
    }

    return (
        <article>
            <div className='d-flex justify-content-between mb-3'>
                <h3 className='m-0'>Categories</h3>
                <a href='/categories/add' className='btn btn-primary'>Add Category</a>
            </div>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        categories.map((category, index) => {
                            return (
                                <tr key={index}>
                                    <td>{category.name}</td>
                                    <td style={{ width: "1%", zIndex: 10 }} onClick={() => { navigate(`/categories/edit/${category.id}`) }}>
                                        <button className='btn btn-primary'>Edit</button>
                                    </td>
                                    <td style={{ width: "1%" }} onClick={() => { deleteCategory(category.id) }}>
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