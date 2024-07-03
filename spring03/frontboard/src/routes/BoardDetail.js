import { useParams } from "react-router-dom";

function BoardDetail(){

    const params = useParams();
    console.log(params.bno);

    return (
        <div className="container">
            <div>
                <h1>boardDetail {params.bno}</h1>
            </div>
        </div>
    );
}

export default BoardDetail;