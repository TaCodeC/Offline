#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

uniform float uTime;
varying vec2 vUV;

vec3 hsv2rgb(vec3 c) {
    vec3 p = abs(fract(c.xxx + vec3(0.0, 2.0/3.0, 1.0/3.0)) * 6.0 - 3.0);
    vec3 rgb = clamp(p - 1.0, 0.0, 1.0);
    return c.z * mix(vec3(1.0), rgb, c.y);
}

void main() {
    float speed = 0.2;
    float hue = fract(vUV.y + uTime * speed);

    float sat = 1.0;
    float val = 0.8;

    vec3 color = hsv2rgb(vec3(hue, sat, val));

    gl_FragColor = vec4(color, 1.0);
}
