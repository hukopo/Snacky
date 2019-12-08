import * as React from "react";
import { Map, Placemark, YMaps } from "react-yandex-maps";
import { Button } from "../Button/Button";
import { LocationPickerY } from "../LocationPicker/LocationPicker";
import { ModalWindow, ModalWindowProps } from "../ModalWindow/ModalWindow";

interface AddCardModalProps extends ModalWindowProps {}
//interface HTMLEventType extends HTMLInputElement, HTMLTextAreaElement {}

interface AddCardModalState {
  title: string;
  description: string;
  place: string;
  members: string;
  tags: string;
  startDate: string;
  endDate: string;
}

interface dalState {
  x: string;
  y: string;
  mapV: boolean;
}

export class AddCardModal extends React.Component<
  AddCardModalProps,
  AddCardModalState & dalState
> {
  state: AddCardModalState & dalState = {
    x: "33",
    y: "33",
    mapV: false,
    title: "",
    description: "",
    members: "",
    place: "",
    tags: "",
    startDate: "0001-01-01T01:01",
    endDate: "0001-01-01T01:01"
  };

  sendCard = async (): Promise<void> => {
    const state = this.state;
    const splitedTags = this.state.tags
      .split(" ")
      .map(t => ({ name: t } as TagModel));
    const card: CardDto = {
      creator: { userName: "", email: "" },
      title: state.title,
      description: { content: state.description },
      tags: splitedTags,
      members: [],
      startDate: state.startDate,
      endDate: state.startDate,
      place: {
        name: "place",
        longitude: parseFloat(state.x),
        latitude: parseFloat(state.y)
      }
    };
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    await fetch("/cards/add", {
      headers: myHeaders,
      method: "POST",
      body: JSON.stringify(card)
    });

    this.props.close();
  };

  handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {
      target: { id, value }
    } = event;
    this.setState({ [id]: value } as Pick<
      AddCardModalState,
      keyof AddCardModalState
    >);
  };

  handleTextAreaChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const {
      target: { id, value }
    } = event;
    this.setState({ [id]: value } as Pick<
      AddCardModalState,
      keyof AddCardModalState
    >);
  };

  render() {
    return (
      <ModalWindow close={this.props.close}>
        <div>
          <label htmlFor="title">название</label>
          <input onChange={this.handleInputChange} id="title" />
        </div>
        <div>
          <label htmlFor="description">описание</label>
          <textarea onChange={this.handleTextAreaChange} id="description" />
        </div>
        <div>
          <label htmlFor="members">участники</label>
          <input onChange={this.handleInputChange} id="members" />
        </div>
        <div>
          <label htmlFor="tags">тэги</label>
          <input onChange={this.handleInputChange} id="tags" />
        </div>
        <div>
          <label htmlFor="place">место</label>
          <input onChange={this.handleInputChange} id="place" />
        </div>
        <div>
          <label htmlFor="startDate">Дата начала</label>
          <input
            onChange={this.handleInputChange}
            type="datetime-local"
            id="startDate"
          />
        </div>
        <div>
          <label htmlFor="endDate">Дата конца</label>
          <input
            onChange={this.handleInputChange}
            //for debug            onChange={e => console.log(e.target.value)}
            type="datetime-local"
            id="endDate"
          />
        </div>
        <div>
          <label htmlFor="x">x</label>
          <input onChange={this.handleInputChange} id="x" />
          <label htmlFor="y">y</label>
          <input onChange={this.handleInputChange} id="y" />
        </div>

        <LocationPickerY />

        <Button text="найти" onClick={() => this.setState({ mapV: true })} />
        <YMaps>
          <div>карта</div>
          {this.state.mapV && (
            <Map
              defaultState={{
                center: [parseFloat(this.state.x), parseFloat(this.state.x)],
                zoom: 10
              }}
            >
              <Placemark
                geometry={[parseFloat(this.state.x), parseFloat(this.state.x)]}
              />
            </Map>
          )}
        </YMaps>
        <Button text="Добавить карточку" onClick={this.sendCard} />
      </ModalWindow>
    );
  }
}
