import Tree from "react-d3-tree"
import { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useCenteredTree } from "../helper";
import {renderRectSvgNode} from '../container'
import card from '../assets/card.png';
import crossIcon from '../assets/cross-icon.png';

export default function TreeStruct() {
    const { houseName } = useParams();
    const [data, setData] = useState([]);
    const containerStyles = {
        width: "100%",
        height: "100vh",
    };
    const containerStylesSingle = {
        width: "50%",
        height: "50vh",
    };

    function depth(i) {
        if(i.children.length) {
            var count = 1;
            i.children.forEach(j => {
                count += depth(j);
            })
            return count;
        }
        return 1;
    }

    useEffect(() => {
        axios.get('http://localhost:8080/houses/' + houseName)
        .then(response => {
            var temp = response.data;
            temp.forEach(i => {
                i["depth"] = depth(i);
            })
            temp.sort((a, b) => {
                if(a.depth > b.depth) return -1;
                return 1;
            })
            setData(temp);
        })
        .catch(error => {
            console.error(error);
        });
    }, []);
    const scaleExtent = { min: 1, max: 1 };

    const renderRectSvgNode = ({ nodeDatum, toggleNode }) => (
        <g>
            <rect
            x="-75"
            y="-200"
            width="150"
            height="150"
            fill="#1E2428"
            />
            <image
            x="-62"
            y="-190"
            width="120"
            xlinkHref={nodeDatum.imageThumb}
            />
            <image
            x="-85"
            y="-200"
            width="170"
            xlinkHref={card}
            />
            <text x="-3" y="5" textAnchor="middle" class="gotText">
            {nodeDatum.characterName}
            </text>
            <text x="-3" y="-30" textAnchor="middle" class="gotText house">
            {nodeDatum.houseName}
            </text>
        </g>
    );

    const calculateTranslate = (dimensions) => {
        if (dimensions) {
            return { x: dimensions.width / 2, y: 200 };
        }
        return { x: 0, y: 0 };
    };

const TreeContainer = ({ data_, renderRectSvgNode }) => {
    const containerRef = useRef(null);
    const [dimensions, setDimensions] = useState({ width: 0, height: 0 });
    const [translate, setTranslate] = useState({ x: 0, y: 0 });

    useEffect(() => {
        const containerElem = containerRef.current;
        if (containerElem) {
            const { width, height } = containerElem.getBoundingClientRect();
            setDimensions({ width, height });
            setTranslate({ x: width / 2, y: 200 });
        }
    }, [containerRef]);

    return (
        <div
            style={(data_.depth === 1 ? containerStylesSingle : containerStyles)}
            ref={containerRef}
        >
            <Tree
                orientation={"vertical"} 
                key={data_.id}
                renderCustomNodeElement={renderRectSvgNode}
                data={data_}
                dimensions={dimensions}
                translate={translate}
                scaleExtent={{ min: 1, max: 1 }}
                depthFactor={270}
                nodeSize={{x: 200, y: 170}}
                pathFunc={'step'}
            />
        </div>
    );
};

return (
    <>
        {data.map(data_ => (
            <TreeContainer
                key={data_.id}
                data_={data_}
                renderRectSvgNode={renderRectSvgNode}
            />
        ))}
    </>
);


};
