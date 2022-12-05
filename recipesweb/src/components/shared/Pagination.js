import React from 'react';

export default function Pagination({ setPage, pagesCount, pageNumber }) {

    const forLoop = () => {
        return (new Array(pagesCount)).fill(1);
    }

    return (
        <nav className="mt-3">
            <ul className="pagination justify-content-center">
                <li className={"page-item " + (pageNumber > 1 ? "cursor-pointer" : "disabled")}>
                    <div onClick={() => setPage(pageNumber - 1)} className="page-link">Previous</div>
                </li>
                {
                    forLoop().map((i, index) => {
                        return (
                            <li key={index} className={"page-item cursor-pointer " + (pageNumber === index + 1 ? "active" : "")}>
                                <div onClick={() => setPage(index + 1)} className="page-link">{index + 1}</div>
                            </li>
                        )
                    })
                }
                <li className={"page-item " + (pageNumber < pagesCount ? "cursor-pointer" : "disabled")}>
                    <div onClick={() => setPage(pageNumber + 1)} className="page-link">Next</div>
                </li>
            </ul>
        </nav>
    );
}