import * as React from "react";

interface BoardsState {
  address: string;
  position: {
    lat: number;
    lng: number;
  };
}

export class LocationPickerY extends React.Component<{}, BoardsState> {
  render() {
    return <div></div>;
  }
}
