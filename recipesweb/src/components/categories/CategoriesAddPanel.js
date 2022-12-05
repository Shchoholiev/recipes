import React, { useEffect, useState } from 'react';
import Pagination from '../shared/Pagination';
import CategoriesService from './CategoriesService';

export default function CategoriesAddPanel({ chooseCategory, chosenCategory }) {

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

    const onSubmit = async () => {
        let input = document.getElementById("categoryName");
        let name = input.value;
        let data = {
            name: name
        }
        await CategoriesService.add(data);
        await getPage(pageNumber);
        input.value = "";
    }

    return (
        <section className='d-flex gap-3 flex-column'>
            <div className='d-flex gap-2 flex-wrap'>
                {
                    categories.map((category, index) => {
                        return (
                            <div key={index} className={"rounded p-2 category cursor-pointer " + (category.id === chosenCategory.id ? "bg-primary text-white" : "bg-light")}
                                onClick={ () => chooseCategory(category) } >
                                {category.name}
                            </div>
                        );
                    })
                }
            </div>
            <div className='d-flex gap-2'>
                <input style={{flex: 1}} className="form-control" id="categoryName"></input>
                <div className='btn btn-primary' onClick={onSubmit}>Add</div>
            </div>
            <Pagination setPage={getPage} pagesCount={pagesCount} pageNumber={pageNumber}></Pagination>
        </section>
    );
}