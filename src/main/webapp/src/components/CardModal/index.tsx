import * as React from "react";
import { Map, Placemark, YMaps } from "react-yandex-maps";
import { Button } from "../Button/Button";
import { LocationPickerY } from "../LocationPicker/LocationPicker";
import { ModalWindow, ModalWindowProps } from "../ModalWindow/ModalWindow";

interface CardModalState {
  title: string;
  description: string;
  place: string;
  members: string;
  tags: string;
  startDate: string;
  endDate: string;
}

interface ModalState {
  x: string;
  y: string;
  mapV: boolean;
}

interface CardModalProps extends ModalWindowProps {
  edit: boolean;
  card?: CardDto;
}

const initState: CardModalState & ModalState = {
  title: "",
  description: "",
  x: "33",
  y: "33",
  mapV: false,
  members: "",
  place: "",
  tags: "",
  startDate: "0001-01-01T01:01",
  endDate: "0001-01-01T01:01"
};

export default class CardModal extends React.Component<
  CardModalProps,
  CardModalState & ModalState
> {
  constructor(props: CardModalProps) {
    super(props);
    const card = this.props.card;
    this.state = card
      ? {
          ...initState,
          title: card.title,
          description: card.description.content,
          place: card.place.name,
          x: card.place.latitude.toString(),
          y: card.place.longitude.toString(),
          startDate: card.startDate,
          endDate: card.endDate
        }
      : initState;
  }

  getCardSnapshot = () => {
    const state = this.state;
    const splitedTags = state.tags
      .split(" ")
      .map(t => ({ name: t } as TagModel));
    const splitedMembers = state.tags
      .split(" ")
      .map(t => ({ name: t } as TagModel));
    return {
      creator: this.props.card
        ? this.props.card.creator
        : { userName: "", email: "" },
      title: state.title,
      description: { content: state.description },
      tags: splitedTags,
      members: splitedMembers,
      startDate: state.startDate,
      endDate: state.startDate,
      place: {
        name: "",
        longitude: parseFloat(state.x),
        latitude: parseFloat(state.y)
      }
    };
  };

  editCard = async (): Promise<void> => {
    if (!this.props.card) throw new Error("не задан id");
    const card = this.getCardSnapshot();
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    await fetch(`/cards/${this.props.card.id}/edit`, {
      method: "PATCH",
      headers: myHeaders,
      body: JSON.stringify(card)
    });
    this.props.close();
  };

  sendCard = async (): Promise<void> => {
    const card = this.getCardSnapshot();
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
      CardModalState,
      keyof CardModalState
    >);
  };

  handleTextAreaChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const {
      target: { id, value }
    } = event;
    this.setState({ [id]: value } as Pick<
      CardModalState,
      keyof CardModalState
    >);
  };

  render() {
    return (
      <ModalWindow close={this.props.close}>
        <div>
          <label htmlFor="title">название</label>
          <input
            onChange={this.handleInputChange}
            id="title"
            value={this.state.title}
          />
        </div>
        <div>
          <label htmlFor="description">описание</label>
          <textarea
            onChange={this.handleTextAreaChange}
            id="description"
            value={this.state.description}
          />
        </div>
        <div>
          <label htmlFor="members">участники</label>
          <input
            onChange={this.handleInputChange}
            id="members"
            value={this.state.members}
          />
        </div>
        <div>
          <label htmlFor="tags">тэги</label>
          <input
            onChange={this.handleInputChange}
            id="tags"
            value={this.state.tags}
          />
        </div>
        <div>
          <label htmlFor="place">место</label>
          <input
            onChange={this.handleInputChange}
            id="place"
            value={this.state.place}
          />
        </div>
        <div>
          <label htmlFor="startDate">Дата начала</label>
          <input
            onChange={this.handleInputChange}
            type="datetime-local"
            id="startDate"
            value={this.state.startDate}
          />
        </div>
        <div>
          <label htmlFor="endDate">Дата конца</label>
          <input
            onChange={this.handleInputChange}
            type="datetime-local"
            id="endDate"
            value={this.state.endDate}
          />
        </div>
        <div>
          <label htmlFor="x">x</label>
          <input
            onChange={this.handleInputChange}
            id="x"
            value={this.state.x}
          />
          <label htmlFor="y">y</label>
          <input
            onChange={this.handleInputChange}
            id="y"
            value={this.state.y}
          />
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
        {this.props.edit ? (
          <Button text="Сохранить" onClick={this.editCard} />
        ) : (
          <Button text="Добавить карточку" onClick={this.sendCard} />
        )}
      </ModalWindow>
    );
  }
}
