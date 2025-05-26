#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

attribute vec4 position;
attribute vec3 normal;
uniform mat4 projection;
uniform mat4 modelview;
uniform float uTime;

varying vec2 vUV;
varying vec3 vNormal;

void main() {
    // Parámetros de las ondas
    float amp1 = 8.0;
    float freq1 = 0.2;
    float speed1 = 0.5;
    
    float amp2 = 5.0;
    float freq2 = 0.15;
    float speed2 = 0.5;
    
    float amp3 = 8.0;
    float freq3 = 0.25;
    float speed3 = 0.5;

    vec3 pos = position.xyz;

    // Tres ondas combinadas
    float w1 = sin(pos.x * freq1 + uTime * speed1) * amp1;
    float w2 = cos(pos.z * freq2 - uTime * speed2) * amp2;
    float w3 = sin((pos.x + pos.z) * freq3 + uTime * speed3) * amp3;

    // Desplaza en la dirección de la normal
    float displacement = w1 + w2 + w3;
    pos += normal * displacement;

    // Recalcula UV tras la distorsión 
    float scaleUV = 0.1;
    vUV = pos.xz * scaleUV;

    // Normales transformadas para iluminación
    vNormal = normalize((modelview * vec4(normal, 0.0)).xyz);

    gl_Position = projection * modelview * vec4(pos, 1.0);
}


/*
#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

attribute vec4 position;
attribute vec3 normal;
uniform mat4 projection;
uniform mat4 modelview;
uniform float uTime;

varying vec2 vUV;
varying vec3 vNormal;

void main() {
    float waveFreq = 4.0;  
    float waveAmp  = 30.0;   

    vec3 pos = position.xyz;

    float wave = sin((pos.x + uTime) * waveFreq)
               * cos((pos.z + uTime) * waveFreq);
    pos += normal * wave * waveAmp;

    float scaleUV = 0.1;
    vUV = pos.xz * scaleUV;

    vNormal = normalize((modelview * vec4(normal, 0.0)).xyz);

    gl_Position = projection * modelview * vec4(pos, 1.0);
}
*/