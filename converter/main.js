$(function() {
    $('button').click(function() {
        var output, size, i, imageData, input = $('textarea').val(),
        canvas = document.querySelector('canvas'),
        ctx = canvas.getContext('2d');

        size = Math.sqrt(input.length / 4) + 1;
        canvas.width = size;
        canvas.height = size;

        imageData = ctx.getImageData(0, 0, size, size);
        for (i = 0; i < input.length; i++) {
            imageData.data[i] = input.charCodeAt(i);
        }
        ctx.putImageData(imageData, 0, 0);

        output = canvas.toDataURL('image/png');
        $('p.output').text(output);
        $('img.output').attr('src', output);
    });
});

