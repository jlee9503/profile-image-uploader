import React, { useEffect, useState } from "react";
import axios from "axios";
import ImageDropZone from "./ImageDropZone";
import "./UserProfile.css";

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
					<div key={index} className="profile">
						{user.profileImageLink ? (
							<img
								src={`http://localhost:8080/api/v1/user-profile/${user.userId}/image/download`}
								alt="User Profile"
								className="profile__image"
							/>
						) : (
							<h1 style={{ marginBottom: "40px" }}>
								Upload the image to set your profile...
							</h1>
						)}

						<h1>Name: {user.userName}</h1>
						<p style={{ marginBottom: "10px" }}>ID: {user.userId}</p>
						<ImageDropZone userId={user.userId} newUserList={fetchData} />
					</div>
				);
			})}
		</div>
	);
};

export default UserProfile;
