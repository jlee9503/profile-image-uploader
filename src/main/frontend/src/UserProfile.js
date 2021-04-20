import React, { useEffect, useState } from "react";
import axios from "axios";
import ImageDropZone from './ImageDropZone';

const UserProfile = () => {
	const [userList, setuserList] = useState([]);

	const fetchData = () => {
		axios.get("http://localhost:8080/api/v1/user-profile").then((res) => {
			setuserList(res.data);
		});
	};

	useEffect(() => {
		fetchData();
	}, []);

	return (
		<div className="profile__container">
			{userList.map((user, index) => {
				return (
					<div key={index}>
						<h1>{user.userName}</h1>
            <p>{user.userId}</p>
            <ImageDropZone userId={user.userId} />
					</div>
				);
			})}
		</div>
	);
};

export default UserProfile;
