export const renderRectSvgNode = ({ nodeDatum, toggleNode }) => (
  //   <g>
  //     <rect width="20" height="20" x="-10" onClick={toggleNode} />
  //     <text fill="black" strokeWidth="1" x="20">
  //       {nodeDatum.characterName}
  //     </text>
  //     {nodeDatum.attributes?.department && (
  //       <text fill="black" x="20" dy="20" strokeWidth="1">
  //         Department: {nodeDatum.attributes?.department}
  //       </text>
  //     )}
  //   </g>
  <g>
    <rect
      x="0"
      y="0"
      width="100"
      height="150"
      fill="#F2F2F2"
      stroke="#333333"
      strokeWidth="2"
    />
    <image
      x="25"
      y="50"
      width="50"
      height="50"
      xlinkHref={nodeDatum.imageThumb}
    />
    <text x="50" y="125" textAnchor="middle" fontSize="10" fill="#333333">
      {nodeDatum.characterName}
    </text>
    <text x="50" y="135" textAnchor="middle" fontSize="8" fill="#666666">
      House: {nodeDatum.houseName}
    </text>
    {/* Add more details as needed */}
  </g>
);
