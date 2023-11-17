ffmpeg \
    -i ./input_example.mp4 \
    -f mp4 \
    -vcodec mjpeg \
    -huffman default \
    -acodec aac \
    -b:a 128k \
    -f mp4 \
    output_huffman.mp4 