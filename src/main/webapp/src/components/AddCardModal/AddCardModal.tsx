import * as React from "react";
import { ModalWindow } from "../ModalWindow/ModalWindow";

export class AddCardModal extends React.Component {
  render() {
    return (
      <ModalWindow close={() => this.setState({ addCardModalIsOpen: false })}>
        <form method="post">
          <label htmlFor="title">название</label>
          <input id="title" />
        </form>
        <div>
          <label htmlFor="description">описание</label>
          <textarea id="description" />
        </div>
        <div>
          <label htmlFor="members">участники</label>
          <input id="members" />
        </div>
        {/* <div>
          <label htmlFor="tags">тэги</label>
          <input id="tags" />
        </div> */}
        <form>
          <label htmlFor="place">место</label>
          <input id="place" />
        </form>
      </ModalWindow>
    );
  }
}
