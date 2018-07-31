//import axios from './assets/axios/axios.js';

/**
 * Chart size config
 */
let maxCount = 1000;
let currentLayout = 'grid';
const CHART_SIZE_CONFIG = {
  grid: {
    charts: 9,
  },
  column: {
    charts: 3,
  },
};

/**
 * Base URL and API URL config
 */
const BASE_URL = '';
const GET_RANDOM_CHART = `${BASE_URL}/chart/data`;

/**
 * Utility functions
 */
// Pure functions

// Impure functions
const convertUnderlayCallback = (areas, color) => (canvas, area, g) => {
  canvas.fillStyle = color || 'rgba(255, 255, 102, 1.0)';
  Array.isArray(areas) && areas.forEach(([start, end]) => {
    const canvasLeft = g.toDomXCoord(start);
    const canvasRight = g.toDomXCoord(end);
    const canvasWidth = canvasRight - canvasLeft;
    canvas.fillRect(canvasLeft, area.y, canvasWidth, area.h);
  });
};

const changeLayout = layout => currentLayout = layout;

/**
 * API functions
 */
const getRandomCharts = (num, point) => axios
  .get(`${GET_RANDOM_CHART}/${point}/${num}`)
  .then(res => res.data.map(chart => !chart.options
    ? chart
    : !chart.options.underlayCallback
      ? chart
      : {
          ...chart,
          options: {
            ...chart.options,
            underlayCallback: convertUnderlayCallback(
              chart.options.underlayCallback.areas,
              chart.options.underlayCallback.color,
            ),
          }
        }
  ))
  .catch(err => {
    console.log(err);
    return [];
  });

/**
 * DOM manipulate functions
 */

const generateOptions = list => list.map(option => {
  const el = document.createElement('option');
  el.setAttribute('value', option.value);
  el.textContent = option.name;
  return el;
});

const appendChild = (parent, child) => parent.appendChild(child);

const appendChildren = (parent, children) => {
  const frag = document.createDocumentFragment();
  children.forEach(child => appendChild(frag, child));
  appendChild(parent, frag);
};

const drawCharts = selectorPrefix => charts => charts.forEach((chart, idx) => {
  const wrapper = document.querySelector(`${selectorPrefix}-${idx}`);
  new Dygraph(
    wrapper,
    chart.data,
    {
      title: chart.title,
      labels: ['X', 'LCL', 'Data', 'UCL'],
      rollPeriod: 7,
    },
  );
});

const toggleLayout = layout => {
  if (layout === 'grid') {
    const gridContainerClassList = document.querySelector('main .charts-grid-layout').classList;
    const columnContainerClassList = document.querySelector('main .charts-column-layout').classList;
    gridContainerClassList.remove('d-none');
    gridContainerClassList.add('d-flex');
    columnContainerClassList.remove('d-flex');
    columnContainerClassList.add('d-none');
  } else if (layout === 'column') {
    const gridContainerClassList = document.querySelector('main .charts-grid-layout').classList;
    const columnContainerClassList = document.querySelector('main .charts-column-layout').classList;
    gridContainerClassList.remove('d-flex');
    gridContainerClassList.add('d-none');
    columnContainerClassList.remove('d-none');
    columnContainerClassList.add('d-flex');
  }
};

const cleanupCharts = () => Array.from(document.querySelectorAll('main .js-sel-chart')).forEach(container => container.childNodes.forEach(child => child.remove()));



window.onload = () => {
  document.querySelector('.switch > input').addEventListener('change', evt => {
    if (evt.target.checked) {
      currentLayout = 'column';
    } else {
      currentLayout = 'grid';
    }
  });
  document.querySelector('.js-sel-render-button').addEventListener('click', () => {
    cleanupCharts();
    toggleLayout(currentLayout);
    getRandomCharts(CHART_SIZE_CONFIG[currentLayout].charts, maxCount)
      .then(drawCharts(`.charts-${currentLayout}-layout .js-sel-chart`));
  });
  document.querySelector('.previous-button').addEventListener('click', () => {
    cleanupCharts();
    getRandomCharts(CHART_SIZE_CONFIG[currentLayout].charts, maxCount)
      .then(drawCharts(`.charts-${currentLayout}-layout .js-sel-chart`));
  });
  document.querySelector('.next-button').addEventListener('click', () => {
    cleanupCharts();
    getRandomCharts(CHART_SIZE_CONFIG[currentLayout].charts, maxCount)
      .then(drawCharts(`.charts-${currentLayout}-layout .js-sel-chart`));
  });
  document.querySelector('.max-count').addEventListener('input', evt => {
    const value = evt.target.value;
    maxCount = parseInt(value, 10);
  });
};
