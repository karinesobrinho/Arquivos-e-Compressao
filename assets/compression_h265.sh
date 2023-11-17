ffmpeg \
    -i ./input_example.mp4 \
    -f mp4 \
    -vcodec libx265 \
    -crf 28 \
    -acodec aac \
    -b:a 128k \
    -f mp4 \
    output_h265.mp4