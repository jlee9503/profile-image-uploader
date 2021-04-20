import React, { useCallback } from "react";
import { useDropzone } from "react-dropzone";
import axios from "axios";

const ImageDropZone = ({ userId }) => {
	const onDrop = useCallback((acceptedFiles) => {
		const file = acceptedFiles[0];

		const formdata = new FormData();
		formdata.append("file", file);

		axios
			.post(
				`http://localhost:8080/api/v1/user-profile/${userId}/image/upload`,
				formdata,
				{
					headers: {
						"Content-Type": "multipart/form-data",
					},
				}
			)
			.then(() => {
				console.log("File successfully posted");
			})
			.catch((err) => {
				console.log(err);
			});
	}, []);
	const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

	return (
		<div {...getRootProps()}>
			<input {...getInputProps()} />
			{isDragActive ? (
				<p>Drop the files here ...</p>
			) : (
				<p>
					Drag 'n' drop profile image here, or click to select profile image
				</p>
			)}
		</div>
	);
};

export default ImageDropZone;
