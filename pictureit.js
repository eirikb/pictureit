window.onload = function() {
	var canvas = document.createElement('canvas'),
	context = canvas.getContext('2d'),
	image = document.getElementById('image'),
	data,
	i,
    text = [];

	canvas.setAttribute('width', image.width);
	canvas.setAttribute('height', image.width);

	context.drawImage(image, 0, 0);
	data = context.getImageData(0, 0, image.width, image.height).data;
	for (i = 0; i < data.length; i++) {
		if (i % 4 !== 3) {
            text.push(String.fromCharCode(data[i]));
		}
	}
    document.write(text.join(''));
};

