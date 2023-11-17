import { createServer } from 'node:http'
// ler arquivos com stream:
import { createReadStream } from 'node:fs'
import { spawn } from 'node:child_process'

// cors:
createServer(async (request, response) => {
    const headers = {
        'Access-Control-Allow-Origin': "*",
        'Access-Control-Allow-Methods': "*"
    }
    if(request.method === 'OPTIONS') {
        response.writeHead(204, headers)
        response.end()
        return;
    }

    response.writeHead(200, {
        'Content-Type': 'video/mp4'
    })

    const ffmpegProcess = spawn('ffmpeg', [
        '-i', 'pipe:0',
        '-f', 'mp4',
        '-vcodec', 'h264',
    //    '-vcodec', 'libx265',
    //    '-vcodec', 'mjpeg',
    //    '-huffman', 'default',
        '-acodec', 'aac',
        '-movflags', 'frag_keyframe+empty_moov+default_base_moof',
        '-b:v', '1500k',
        '-maxrate', '1500k',
        '-bufsize', '1000k',
        '-f', 'mp4',
        'pipe:1'
    ], {
        stdio: ['pipe', 'pipe', 'pipe']
    })

    // video comprimido
     createReadStream('./assets/output_example.mp4').pipe(ffmpegProcess.stdin)
    // createReadStream('./assets/output_huffman.mp4').pipe(ffmpegProcess.stdin)
    //createReadStream('./assets/output_h265.mp4').pipe(ffmpegProcess.stdin)
    ffmpegProcess.stderr.on('data', msg => console.log(msg.toString()))
    ffmpegProcess.stdout.pipe(response)
    
    // video encerrado bruscamente
    request.once('close', () => {
        ffmpegProcess.stdout.destroy()
        ffmpegProcess.stdin.destroy()
        console.log('disconnected!', ffmpegProcess.kill())
    })
    
    // video original
    //createReadStream('./assets/input_example.mp4')
    //.pipe(response)

    // response.end('hello')
})
.listen(3000, () => console.log('server is running at 3000'))