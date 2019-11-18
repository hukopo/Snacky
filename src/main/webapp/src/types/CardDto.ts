interface CardDto {
  creator: UserModel;
  title: string;
  description: DescriptionModel;
  members: UserModel[];
  tags: TagModel[];
  place: PlaceModel;
  startDate: string;
  endDate: string;
}

interface DescriptionModel {
  content: string;
}

interface UserModel {
  userName: string;
  email: string;
}

interface PlaceModel {
  name: string;
  longitude: number;
  latitude: number;
}

interface TagModel {
  name: string;
}
