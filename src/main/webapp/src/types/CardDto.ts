interface CardDto {
  title: String;
  description: DescriptionModel;
  members: UserModel[];
  creator: UserModel;
  place: PlaceModel;
  startDate: Date;
  endDate: Date;
}

interface DescriptionModel {
  content: string;
}

interface UserModel {
  username: string;
  email: string;
}

interface PlaceModel {
  name: string;
  longitude: number;
  latitude: number;
}
