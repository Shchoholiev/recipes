import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Pagination({ setPage, pagesCount, pageNumber }) {

    const forLoop = () => {
        return (new Array(pagesCount)).fill(1);
    }

    return (
        <nav className="mt-3">
            <ul className="pagination justify-content-center">
                <li className={"page-item " + (pageNumber > 1 ? "cursor-pointer" : "disabled")}>
                    <a onClick={() => setPage(pageNumber - 1)} className="page-link">Previous</a>
                </li>
                {
                    forLoop().map((i, index) => {
                        return (
                            <li key={index} className={"page-item cursor-pointer " + (pageNumber === index + 1 ? "active" : "")}>
                                <a onClick={() => setPage(index + 1)} className="page-link">{index + 1}</a>
                            </li>
                        )
                    })
                }
                <li className={"page-item " + (pageNumber < pagesCount ? "cursor-pointer" : "disabled")}>
                    <a onClick={() => setPage(pageNumber + 1)} className="page-link">Next</a>
                </li>
            </ul>
        </nav>
    );
}